pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    stages {


        stage ('Test'){
            steps{
                echo 'Testeando...'  
                withGradle{          
                    sh './gradlew clean test'
                }
            }
                post{
                    always{
                        junit 'build/test-results/test/TEST-*.xml'
                        jacoco execPattern:'build/jacoco/*.exec'

                    }
                }

            

        }
        stage('QA'){
            steps{
                echo 'Checking...'
                withGradle{
                    sh './gradlew check'
                    
            }
        }
        post{
            recordIssues{
                tools: [
                    pmdParser(pattern: 'build/reports/pmd/*.xml')
                        ]
                    }
                }
        
                }

        stage('Build') {
            steps {
                echo 'Buildeando...'
                withGradle {
    // some block. las comillas triples es para instrucciones de varias lineas.
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
                //sh 'docker-compose up -d'
                //'java -jar  build/libs/hello-srping-0.0.1-SNAPSHOT.jar' --> aqui tira directamente del .jar

    // Parte de ssh Agent
                sshagent (credentials: ['sshJenkins']) {        
                    sh 'git tag MAIN-1.1.${BUILD_NUMBER}'
                    sh 'git push --tags' //Se le puede poner en lugar de todos los cambios, hacerlo por el tag que queramos
                }

            }
        }

          stage('gitlab'){
            steps{
                echo 'Notify Gitlab'
                updateGitlabCommitStatus name: 'build' , state: 'pending'
                updateGitlabCommitStatus name: 'build' , state: 'success'

            }
        }

        }
 }

