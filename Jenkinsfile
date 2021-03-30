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
              sed -i "s/IMAGETAG/${IMAGEREPO}\\/${IMAGETAG}/" k8s/test_deployment.yaml'''
        sh 'cat k8s/test_deployment.yaml'
        sh 'microk8s kubectl apply -f k8s/test_deployment.yaml'
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

      IMAGEREPO='klucsik.duckdns.org:8081'
    }
}