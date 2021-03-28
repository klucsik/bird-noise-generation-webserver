install maven
$ sudo apt install maven
add write rights to the jenkins user to the maven local repo
$ sudo chown -R jenkins:jenkins /var/lib/jenkins/.m2
