pipeline {
  agent {
    kubernetes {
      yamlFile 'k8s/agents/jenkins-agent-allinone.yaml'
      defaultContainer 'docker'
      //idleMinutes 60
    }
  }

  stages {
    stage('build dependencies') {
        parallel {
            stage('dtos'){
                steps {
                    container(name: 'maven') {
                        sh 'mvn -B -DskipTests -f backend/backendclient/pom.xml clean package install'
                    }
                }
            }
        }
    }
    stage('update version in frontend') {
            steps {
                    sh 'sed -i "s/JENKINS_WILL_CHANGE_THIS_VERSION_AND_DATE/$VERSION $(date)/" frontend/src/main/resources/templates/fragments/_menu.html'
            }
        }

    stage('build images') {
      parallel {
        stage('backend') {
          when {
            anyOf {
              changeset 'backend/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${BE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            sh 'cp backend/backendserver/src/main/resources/prod_properties backend/backendserver/src/main/resources/application.properties' //use psql server
            container(name:'maven'){
               sh 'mvn -B -DskipTests -f backend/pom.xml clean package install'

                }
            sh 'docker buildx create  --driver kubernetes --name builder --node arm64node  --driver-opt replicas=1,nodeselector=kubernetes.io/arch=arm64 --use'
            sh 'docker buildx create --append --driver kubernetes --name builder --node amd64node  --driver-opt replicas=1,nodeselector=kubernetes.io/arch=amd64 --use'
            sh 'docker buildx build -t ${IMAGEREPO}/${BE_IMAGETAG} --platform linux/arm64,linux/amd64 --push backend/backendserver/. '
            sh 'sed -i "s/BE_JENKINS_WILL_CHANGE_THIS_WHEN_REDEPLOY_NEEDED_BASED_ON_CHANGE/$(date)/" k8s/birdnoise_deployment.yaml'
          }
        }

        stage('frontend') {
          when {
            anyOf {
              changeset 'frontend/**'
              expression {
                image_id = sh (script: "docker images -q ${IMAGEREPO}/${FE_IMAGETAG}", returnStdout: true).trim()
                if (image_id.isEmpty()) return true
              }

            }

          }
          steps {
            container(name: 'maven') {
              sh 'mvn -B -DskipTests -f frontend/pom.xml clean package install'
            }

            sh 'docker buildx build -t ${IMAGEREPO}/${FE_IMAGETAG} --platform linux/arm64,linux/amd64 --push frontend/.'
            sh 'sed -i "s/FE_JENKINS_WILL_CHANGE_THIS_WHEN_REDEPLOY_NEEDED_BASED_ON_CHANGE/$(date)/" k8s/birdnoise_deployment.yaml'
          }
        }

      }
    }

    stage('deploy ') {
      steps {
        sh '''
        cp -i k8s/birdnoise_deployment.yaml k8s/deployment.yaml
        sed -i "s/BRANCHNAME/${BRANCH_NAME_LC}/" k8s/deployment.yaml
        sed -i "s/BE_IMAGETAG/${IMAGEREPO}\\/${BE_IMAGETAG}/" k8s/deployment.yaml
        sed -i "s/FE_IMAGETAG/${IMAGEREPO}\\/${FE_IMAGETAG}/" k8s/deployment.yaml
        '''
        sh 'cat k8s/deployment.yaml'
        container(name: 'kubectl') {
        sh 'kubectl apply -f k8s/deployment.yaml'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=birdnoise-${BRANCH_NAME_LC}'
        sh 'kubectl rollout status deployment/birdnoise-fe --namespace=birdnoise-${BRANCH_NAME_LC}'

        sh '''curl --location --request POST $DISCORD_URL         --header \'Content-Type: application/json\'         --data-raw \'{"content": "I am pleased to report that I am deployed the branch:** \'${BRANCH_NAME_LC}\'** and its available for you at: http://\'${BRANCH_NAME_LC}\'.birdnoise.klucsik.fun "}\'
        '''
        }
      }
    }

    stage('api-tests') {
      when {
        changeset 'backend/**'
      }
      steps {
        sh 'cp k8s/birdnoise_deployment.yaml k8s/test_deployment.yaml'
        sh 'sed -i "s/BRANCHNAME/${TEST_BRANCNAME}/" k8s/test_deployment.yaml'
        sh 'sed -i "s/BE_IMAGETAG/${IMAGEREPO}\\/${BE_IMAGETAG}/" k8s/test_deployment.yaml'
        sh 'sed -i "s/FE_IMAGETAG/${IMAGEREPO}\\/${FE_IMAGETAG}/" k8s/test_deployment.yaml'
        sh 'cat k8s/test_deployment.yaml'
        container(name: 'kubectl') {
        sh 'kubectl apply -f k8s/test_deployment.yaml'
        sh 'kubectl rollout status deployment/birdnoise-be --namespace=birdnoise-${TEST_BRANCNAME}'
        }
        sh 'sed -i "s/BRANCHNAME/${TEST_BRANCNAME}/" api-tests/birdnoise-BE-remote.postman_environment.json'
        sh 'cat api-tests/birdnoise-BE-remote.postman_environment.json'
        container(name: 'newman') {
        sh 'newman run api-tests/birdnoise-tracks.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'newman run api-tests/birdnoise-playUnits.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'newman run api-tests/birdnoise-deviceLog.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'newman run api-tests/birdnoise-deviceCom.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
        sh 'newman run api-tests/birdnoise-deviceVoltage.postman_collection.json -e api-tests/birdnoise-BE-remote.postman_environment.json '
      }
      }
    }
  }
  environment {
    VERSION = """${sh(
                                     script:
                                        "cat version",
                                     returnStdout:true
                                     ).trim()}"""
    BRANCH_NAME_LC = """${sh(
                                   script:
                                      "echo $BRANCH_NAME | sed -e 'y/ABCDEFGHIJKLMNOPQRSTUVWXYZ/abcdefghijklmnopqrstuvwxyz/'",
                                   returnStdout:true
                                   ).trim()}"""
    BE_IMAGETAG = """${sh(
                                  script:
                                    "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 'y/ABCDEFGHIJKLMNOPQRSTUVWXYZ/abcdefghijklmnopqrstuvwxyz/') echo birdnoise_be_$BRANCH_NAME_LC:$VERSION",
                                  returnStdout:true
                                  ).trim()}"""
    FE_IMAGETAG = """${sh(
                                    script:
                                      "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 'y/ABCDEFGHIJKLMNOPQRSTUVWXYZ/abcdefghijklmnopqrstuvwxyz/') echo birdnoise_fe_$BRANCH_NAME_LC:$VERSION",
                                    returnStdout:true
                                    ).trim()}"""
    TEST_BRANCNAME = """${sh(
                                  script:
                                    "BRANCH_NAME_LC=\$(echo $BRANCH_NAME | sed -e 's/\\(.*\\)/\\L\\1/') echo apitest-$BRANCH_NAME_LC",
                                  returnStdout:true
                                  ).trim()}"""
      IMAGEREPO = 'registry.klucsik.fun'
      DISCORD_URL = credentials('discord-url')
        }
  post {
          always {
            container(name: 'kubectl') {
               // sh 'kubectl delete deployment amd64node'
               // sh 'kubectl delete deployment arm64node'
                sh 'kubectl delete ns birdnoise-${TEST_BRANCNAME} > /dev/null 2>&1 >/dev/null'  //make this silent so wont raise errors
            }
          }

      failure {
        container(name: 'kubectl') {
            sh '''curl --location --request POST $DISCORD_URL        --header \'Content-Type: application/json\'         --data-raw \'{"content": "  ->  I am must exspress my deep regret, that the pipeline on the branch ** \'${BRANCH_NAME_LC}\'** had failed. Please check on my logs on what went wrong! "}\''''
        }
      }

      success {
        container(name: 'kubectl') {
            sh '''curl --location --request POST $DISCORD_URL         --header \'Content-Type: application/json\'         --data-raw \'{"content": "  ->  I am pleased to report that the pipeline on branch ** \'${BRANCH_NAME_LC}\'** was a great success, everything is green!"}\''''
        }
      }
  }
}