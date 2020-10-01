def server = Artifactory.server 'MyArtifactory'

def rtMaven = Artifactory.newMavenBuild()

def buildInfo

pipeline {
	 agent any

	tools {
		jdk "Java-1.8"
		maven "Maven-3.5.3"
	}

	stages {
		stage('Clone sources') {
			steps {
				git url: 'https://github.com/srajkumar75/semaphore-demo-java-spring'
			}
		}
		stage('Artifactory configuration'){
			steps{
				script{
					rtMaven.tool = 'Maven-3.5.3'
					rtMaven.deployer releaseRepo: 'libs-release-local',snapshotRepo: 'libs-snapshot-local', server:server
					rtMaven.resolver releaseRepo: 'libs-release',snapshotRepo: 'libs-snapshot', server:server
					rtMaven.deployer.artifactDeploymentPatterns.addExclude("pom.xml")
					buildInfo = Artifactory.newBuildInfo()
					buildInfo.retention maxBuilds: 10, maxDays: 7,deleteBuildArtifacts:true
					buildInfo.env.capture = true
				}
			}
		}
		stage('Execute Maven'){
			steps{
				script{
					//rtMaven.run pom: 'pom.xml',goals: 'clean compile package install verify',buildInfo: buildInfo
					//sh 'docker rm  hopeful_keldysh --force'
					sh 'chmod 777 *'
				}
				script{
					//rtMaven.run pom: 'pom.xml',goals: 'clean compile package install verify',buildInfo: buildInfo
					//sh 'docker rm  hopeful_keldysh --force'
					sh './delimage2.sh'
				}
				
				script{
					//rtMaven.run pom: 'pom.xml',goals: 'clean compile package install verify',buildInfo: buildInfo
					//sh 'docker image rm kkapelon/docker-maven-comparison --force'
					sh './delimage.sh kkapelon/docker-maven-comparison'
				}
				script{
					//rtMaven.run pom: 'pom.xml',goals: 'clean compile package install verify',buildInfo: buildInfo
					sh 'mvn clean compile package install verify'
				}
			}
			
		}

	}
} //Completed

node {
	stage('SCM') {
		git 'https://github.com/srajkumar75/semaphore-demo-java-spring'
	}
	stage('SonarQube analysis') {
		withSonarQubeEnv('SonarQube6.3') {
			sh 'mvn clean package sonar:sonar'
		} // submitted SonarQube taskId is automatically attached to the pipeline context
	}
	stage('Publish build info'){
		script {
			//server.publishBuildInfo buildInfo
			// sh 'mvn docker:start'
		}
	}
}

// No need to occupy a node
stage("Quality Gate"){
	timeout(time: 1, unit: 'HOURS') { 
		def qg = waitForQualityGate() 
		if (qg.status != 'OK') {
			error "Pipeline aborted due to quality gate failure: ${qg.status}"
		}
		node { 
			script {
				//server.publishBuildInfo buildInfo
				sh 'mvn docker:start'
			}
		}
		
		
	}
}

