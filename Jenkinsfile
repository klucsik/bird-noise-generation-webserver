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
        sh 'docker rm -f birdnoise_$BRANCH_NAME || true'
        sh 'docker run  -d --name birdnoise_$BRANCH_NAME -p 8090:8080 klucsik.duckdns.org:8081/birdnoise_$BRANCH_NAME:$GIT_COMMIT'
      }
    }

  }
}