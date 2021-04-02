pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('containerize') {
      steps {
        sh '''docker build -t ${IMAGEREPO}/${IMAGETAG} .
'''
      }
    }

    stage('push to registry') {
      steps {
        sh 'docker push ${IMAGEREPO}/${IMAGETAG}'
      }
    }

    stage('deploy ') {
      steps {
        sh '''sed -i "s/BRANCHNAME/${BRANCH_NAME_LC}/" k8s/test_deployment.yaml
sed -i "s/IMAGETAG/${IMAGEREPO}\\/${IMAGETAG}/" k8s/test_deployment.yaml
cp -i k8s/test_deployment.yaml k8s/${BRANCH_NAME_LC}_deployment.yaml
'''
        sh 'cat k8s/${BRANCH_NAME_LC}_deployment.yaml'
        sh '''microk8s kubectl apply -f k8s/${BRANCH_NAME_LC}_deployment.yaml
'''
        sh 'microk8s kubectl rollout status deployment/birdnoise-${BRANCH_NAME_LC}'
        sh '''curl --location --request POST \'https://discord.com/api/webhooks/827513686460989490/wWHavHLlBi1FCa_UkoPk8v0nqs9APg9bPWHf63RLhZejSOSPJk1Db57Tc7WXDGK7eU8g\'         --header \'Content-Type: application/json\'         --data-raw \'{"content": "I am pleased to report that Im deployed the branch: ${BRANCH_NAME_LC} and its available for you at: ${BRANCH_NAME_LC}.klucsik.duckdns.org "}\'
        '''
      }
    }

  }
  environment {
    BRANCH_NAME_LC = """${sh(
                  script: 'echo $BRANCH_NAME | sed -e \'s/\\(.*\\)/\\L\\1/\'',
                  returnStdout:true
                  ).trim()}"""
      IMAGETAG = """${sh(
                                       script: "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') \
                                       echo birdnoise_$BRANCH_NAME_LC:$GIT_COMMIT",
                                       returnStdout:true
                                       ).trim()}"""
        IMAGEREPO = 'klucsik.duckdns.org:5000'
      }
    }