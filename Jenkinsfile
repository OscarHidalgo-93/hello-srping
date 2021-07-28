pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    stages {

        stage('Build') {
            steps {
                echo 'Buildeando...'
                withGradle {
    // some block
    sh '''
                 ./gradlew assemble \
       '''
                            }


            }

        post{
            success{
                echo 'Archivando...'
                archiveArtifacts artifacts: 'build/libs/*.jar'
            }
             }
        }
        stage('Deploying') {
            steps {

                echo 'Se arcivó el artefacto, Desplegando...'
                sh 'docker-compose up -d'
            }
        }

        }
}
