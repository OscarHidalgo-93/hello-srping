#!/usr/bin/env groovy
pipeline {

    agent any

    options {
        ansiColor('xterm')
    }

    stages {

        stage('Test') {
            when { expression { false } }
            steps {
                echo 'Testeando...'
                withGradle {
                    sh './gradlew clean test pitest'
                }
            }
            post {
                always {
                    junit 'build/test-results/test/TEST-*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                    recordIssues(enabledForFailure: true,
                            tool: pit(pattern: "build/reports/pitest/**/*.xml"))

                }
            }


        }

        stage ('Analisis') {
            failFast  true
            parallel {
                stage('SonarQube Analysis') {
                    when { expression { false } } //expresion condicional, nos dejara de hacer el test ==> coments
                    steps {
                        withSonarQubeEnv('SonarQube-sever') {
                            sh "./gradlew sonarqube"
                        }

                    }

                }
                stage('QA') {
                    when { expression { false } }
                    steps {
                        echo 'Checking...'
                        withGradle {
                            sh './gradlew clean check'

                        }
                    }
                    post {
                        always {
                            recordIssues(
                                    tools: [
                                            pmdParser(pattern: 'build/reports/pmd/*.xml'),
                                            spotBugs(pattern: 'build/reports/spotbugs/*.xml')
                                    ]
                            )
                        }

                    }

                }


            }
        }


        stage('Build') {
             when { expression { false } }
            steps {
                echo 'Buildeando...'
                sh 'docker-compose build'

                /*  withGradle {
                      // some block. las comillas triples es para instrucciones de varias lineas.
                      sh '''
                   ./gradlew assemble \
                         '''
                  }

                 */


            }

            post {
                success {
                    echo 'Archivando...'
                    //archiveArtifacts artifacts: 'build/libs/*.jar'
                }
            }
        }
        stage('Security') {
             when { expression { false } }
            steps {
                echo 'Security analisis...'
                sh 'trivy image --format=json --output=trivy-image.json hello-srping-pruebas:latest'
            }
            post {
                always {
                    recordIssues(
                            enabledForFailure: true,
                            aggregatingResults: true,
                            tool: trivy(pattern: 'trivy-*.json')


                    )
                }
            }
        }

        stage('Publish') {
             when { expression { false } }
            steps {

                echo 'Se arcivó el artefacto, Publicando...'
                //mirar despliegue con pipeline con sentencia 'when
                //sh 'docker-compose up -d'
                //'java -jar  build/libs/hello-srping-0.0.1-SNAPSHOT.jar' --> aqui tira directamente del .jar

                withDockerRegistry([url:'http://10.250.12.3:5050', credentialsId:'token-dockerRegistry']){
                    sh 'docker tag hello-srping-pruebas:latest 10.250.12.3:5050/oscarh93/hello-srping:PRUEBAS-1.${BUILD_NUMBER}'
                    sh 'docker push 10.250.12.3:5050/oscarh93/hello-srping:PRUEBAS-1.${BUILD_NUMBER}'

                }                

                // Parte de ssh Agent
                sshagent(credentials: ['sshJenkins']) {
                    sh 'git tag MAIN-1.1.${BUILD_NUMBER}'
                    sh 'git push --tags'
                    //Se le puede poner en lugar de todos los cambios, hacerlo por el tag que queramos
                }

            }
        }

        stage('Deploy'){
            steps{
                echo 'Desplegando servicio...'
                sshagent(credentials: ['appkey']){
                    sh '''
                    ssh -i app app@10.250.12.3 'cd hello-spring && docker-compose pull && docker-compose up -d
                    '''

                }
            }
        }

        stage('gitlab') {
            steps {
                echo 'Notify Gitlab'
                updateGitlabCommitStatus name: 'build', state: 'pending'
                updateGitlabCommitStatus name: 'build', state: 'success'

            }
        }

    }
}

