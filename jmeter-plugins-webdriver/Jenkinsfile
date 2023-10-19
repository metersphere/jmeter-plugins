pipeline {
    agent {
        node {
            label 'metersphere'
        }
    }
    options { quietPeriod(600) }
    environment {
        JAVA_HOME = '/opt/jdk-8'
    }
    stages {
        stage('Build/Test') {
            steps {
                configFileProvider([configFile(fileId: 'metersphere-maven', targetLocation: 'settings.xml')]) {
                    sh "./mvnw clean install -Dgpg.skip -DskipTests --settings ./settings.xml"
                }
            }
        }
    }
}
