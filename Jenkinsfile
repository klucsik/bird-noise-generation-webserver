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
        sh '''docker build -t klucsik.duckdns.org:8081/birdnoise_$BRANCH_NAME:$GIT_COMMIT .
'''
      }
    }

    stage('push to registry') {
      steps {
        sh 'docker push klucsik.duckdns.org:8081/birdnoise_$BRANCH_NAME:$GIT_COMMIT'
      }
    }

    stage('deploy ') {
      steps {
        sh '''sed -i "s/BRANCHNAME/$BRANCH_NAME/" k8s/test_deployment.yaml 
sed -i "s/IMAGETAG/klucsik.duckdns.org:8081\\/birdnoise_$BRANCH_NAME:$GIT_COMMIT/" k8s/test_deployment.yaml'''
        sh 'cat k8s/test_deployment.yaml'
        sh 'microk8s kubectl apply -f k8s/test_deployment.yaml'
      }
    }

  }
}