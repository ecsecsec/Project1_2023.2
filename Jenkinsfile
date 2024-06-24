node { 
  stage('SCM') { 
    git branch: 'main', credentialsId: 'thanh1072004-github', url: 'https://github.com/ecsecsec/Project1_2023.2.git' 
  } 
  stage('SonarQube Analysis') { 
   def scannerHome = tool 'SonarQube Scanner'; 
    withSonarQubeEnv() { 
      sh "${scannerHome}/bin/sonar-scanner -Dsonar.java.binaries=.  -Dsonar.projectKey=Nhom2_QuanLySuKien_Project1 -Dsonar.login=sqa_aac2922aa906ca3e7a330beffed228373f2f76e3" 
    } 
  } 
}