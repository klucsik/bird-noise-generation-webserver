pipeline {
  agent any
  stages {
    stage ('build deps'){
        when {
            anyOf {
            changeset 'backend/backendclient/src/main/**'
            }

        }
        steps {
            sh 'mvn -B -DskipTests -f backend/backendclient/pom.xml clean package install'
        }
    }
    stage('build images') {
      parallel {
        stage('backend') {
          when {
            anyOf {
              changeset 'backend/backendserver/src/main/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${BE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            sh 'mvn -B -DskipTests -f backend/pom.xml clean package install'
            sh 'docker build -t ${IMAGEREPO}/${BE_IMAGETAG} backend/backendserver/.'
            sh 'docker push ${IMAGEREPO}/${BE_IMAGETAG}'
            sh 'sed -i "s/BE_JENKINS_WILL_CHANGE_THIS_WHEN_REDEPLOY_NEEDED_BASED_ON_CHANGE/$(date)/" k8s/birdnoise_deployment.yaml'
          }
        }

        stage('frontend') {
          when {
            anyOf {
              changeset 'frontend/src/main/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${FE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            sh 'mvn -B -DskipTests -f frontend/pom.xml clean package install'
            sh 'docker build -t ${IMAGEREPO}/${FE_IMAGETAG} frontend/.'
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
        sh '''curl --location --request POST \'https://discord.com/api/webhooks/827513686460989490/wWHavHLlBi1FCa_UkoPk8v0nqs9APg9bPWHf63RLhZejSOSPJk1Db57Tc7WXDGK7eU8g\'         --header \'Content-Type: application/json\'         --data-raw \'{"content": "I am pleased to report that I am deployed the branch:** \'${BRANCH_NAME_LC}\'** and its available for you at: http://\'${BRANCH_NAME_LC}\'.klucsik.fun "}\'
        '''
      }
    }

    stage('api-tests') {
      steps {
        sh 'cp k8s/apitest_deployment.yaml k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'sed -i "s/BRANCHNAME/${BRANCH_NAME_LC}/" k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'sed -i "s/BE_IMAGETAG/${IMAGEREPO}\\/${BE_IMAGETAG}/" k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'sed -i "s/FE_IMAGETAG/${IMAGEREPO}\\/${FE_IMAGETAG}/" k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'cat k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'kubectl apply -f k8s/${BRANCH_NAME_LC}_apitest_deployment.yaml'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=apitest-${BRANCH_NAME_LC}'
        sh 'sed -i "s/BRANCHNAME/apitest-${BRANCH_NAME_LC}/" api-tests/birdnoise-BE-remote.postman_environment.json'
        sh 'cat api-tests/birdnoise-BE-remote.postman_environment.json'
        sh 'newman run api-tests/birdnoise-tracks.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'kubectl rollout restart deployment/birdnoise-be --namespace=apitest-${BRANCH_NAME_LC}'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=apitest-${BRANCH_NAME_LC}'
        sh 'newman run api-tests/birdnoise-playUnits.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
      }
    }
  }
  post {
      always {
      sh 'kubectl delete ns apitest-${BRANCH_NAME_LC}'
      }
      failure {
      sh '''curl --location --request POST \'https://discord.com/api/webhooks/827513686460989490/wWHavHLlBi1FCa_UkoPk8v0nqs9APg9bPWHf63RLhZejSOSPJk1Db57Tc7WXDGK7eU8g\'         --header \'Content-Type: application/json\'         --data-raw \'{"content": "  ->  I am must exspress my deep regret, that the pipeline on the branch ** \'${BRANCH_NAME_LC}\'** had failed. Please check on my logs on what went wrong! "}\'
              '''
      }
      success{
      sh '''curl --location --request POST \'https://discord.com/api/webhooks/827513686460989490/wWHavHLlBi1FCa_UkoPk8v0nqs9APg9bPWHf63RLhZejSOSPJk1Db57Tc7WXDGK7eU8g\'         --header \'Content-Type: application/json\'         --data-raw \'{"content": "  ->  I am pleased to report that the pipeline on branch ** \'${BRANCH_NAME_LC}\'** was a great success, everything is green!"}\'
              '''
      }
  }
  environment {
    BRANCH_NAME_LC = """${sh(
                             script:
                                'echo $BRANCH_NAME | sed -e \'s/\\(.*\\)/\\L\\1/\'',
                             returnStdout:true
                             ).trim()}"""
    BE_IMAGETAG = """${sh(
                          script:
                            "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') echo birdnoise_be_$BRANCH_NAME_LC",
                          returnStdout:true
                          ).trim()}"""
    FE_IMAGETAG = """${sh(
                          script:
                            "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') echo birdnoise_fe_$BRANCH_NAME_LC",
                          returnStdout:true
                          ).trim()}"""
    IMAGEREPO = 'www.registry.klucsik.fun'
        }
      }