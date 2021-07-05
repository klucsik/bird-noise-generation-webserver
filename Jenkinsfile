pipeline {
  agent any
  stages {
    stage('build images') {
      parallel {
        stage('backend') {
          when {
            anyOf {
              changeset 'src/main/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${BE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            sh 'mvn -B -DskipTests clean package'
            sh 'docker build -t ${IMAGEREPO}/${BE_IMAGETAG} .'
            sh 'docker push ${IMAGEREPO}/${BE_IMAGETAG}'
            sh 'sed -i "s/BE_JENKINS_WILL_CHANGE_THIS_WHEN_REDEPLOY_NEEDED_BASED_ON_CHANGE/$(date)/" k8s/birdnoise_deployment.yaml'
          }
        }

        stage('frontend') {
          when {
            anyOf {
              changeset 'FrontEnd/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${FE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            sh 'docker build -t ${IMAGEREPO}/${FE_IMAGETAG} FrontEnd/.'
            sh 'docker push ${IMAGEREPO}/${FE_IMAGETAG}'
            sh 'sed -i "s/FE_JENKINS_WILL_CHANGE_THIS_WHEN_REDEPLOY_NEEDED_BASED_ON_CHANGE/$(date)/" k8s/birdnoise_deployment.yaml'
          }
        }

      }
    }

    stage('deploy ') {
      steps {
        sh '''
        cp -i k8s/birdnoise_deployment.yaml k8s/${BRANCH_NAME_LC}_deployment.yaml
        sed -i "s/BRANCHNAME/${BRANCH_NAME_LC}/" k8s/${BRANCH_NAME_LC}_deployment.yaml
        sed -i "s/BE_IMAGETAG/${IMAGEREPO}\\/${BE_IMAGETAG}/" k8s/${BRANCH_NAME_LC}_deployment.yaml
        sed -i "s/FE_IMAGETAG/${IMAGEREPO}\\/${FE_IMAGETAG}/" k8s/${BRANCH_NAME_LC}_deployment.yaml
        '''
        sh 'cat k8s/${BRANCH_NAME_LC}_deployment.yaml'
        sh 'kubectl apply -f k8s/${BRANCH_NAME_LC}_deployment.yaml'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=${BRANCH_NAME_LC}'
        sh 'kubectl rollout status deployment/birdnoise-fe --namespace=${BRANCH_NAME_LC}'
        sh '''curl --location --request POST \'https://discord.com/api/webhooks/827513686460989490/wWHavHLlBi1FCa_UkoPk8v0nqs9APg9bPWHf63RLhZejSOSPJk1Db57Tc7WXDGK7eU8g\'         --header \'Content-Type: application/json\'         --data-raw \'{"content": "I am pleased to report that Im deployed the branch:** \'${BRANCH_NAME_LC}\'** and its available for you at: http://\'${BRANCH_NAME_LC}\'.klucsik.duckdns.org "}\'
        '''
      }
    }

    stage('api-tests') {
      steps {
        sh 'cp k8s/birdnoise_deployment.yaml k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'sed -i "s/BRANCHNAME/test-${BRANCH_NAME_LC}/" k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'sed -i "s/BE_IMAGETAG/${IMAGEREPO}\\/${BE_IMAGETAG}/" k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'sed -i "s/FE_IMAGETAG/${IMAGEREPO}\\/${FE_IMAGETAG}/" k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'cat k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'kubectl apply -f k8s/test-${BRANCH_NAME_LC}_deployment.yaml'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=test-${BRANCH_NAME_LC}'
        sh 'kubectl rollout status deployment/birdnoise-fe --namespace=test-${BRANCH_NAME_LC}'
        sh 'sed -i "s/BRANCHNAME/test-${BRANCH_NAME_LC}/" api-tests/birdnoise-BE-remote.postman_environment.json'
        sh 'newman run api-tests/birdnoise-tracks.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'kubectl rollout restart deployment/birdnoise-be --namespace=test-${BRANCH_NAME_LC}'
        sh 'kubectl delete ns test-${BRANCH_NAME_LC}'
      }
    }

  }
  environment {
    BRANCH_NAME_LC = """${sh(
                                                      script: 'echo $BRANCH_NAME | sed -e \'s/\\(.*\\)/\\L\\1/\'',
                                                      returnStdout:true
                                                      ).trim()}"""
      BE_IMAGETAG = """${sh(
                                                                                       script: "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') \
                                                                                       echo birdnoise_be_$BRANCH_NAME_LC",
                                                                                       returnStdout:true
                                                                                       ).trim()}"""
        FE_IMAGETAG = """${sh(
                                                                                               script: "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') \
                                                                                               echo birdnoise_fe_$BRANCH_NAME_LC",
                                                                                               returnStdout:true
                                                                                               ).trim()}"""
          IMAGEREPO = 'klucsik.duckdns.org:5000'
        }
      }