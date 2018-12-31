#/bin/bash

green=`tput setaf 2`
reset=`tput sgr0`

export SONAR_HOST_URL='https://sonarcloud.io'
export SONAR_ORGANIZATION='kherbiche-github'

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  echo "${reset} ${green} ${bold}======================================================${reset}"
  echo "=== Syphax Building and analyzing a regular branch ==="
  echo "${reset} ${green} ${bold}======================================================${reset}"
  
  if [ "$TRAVIS_BRANCH" == "master" ]; then

    echo "${reset} ${green} ${bold}======================================================${reset}"
    echo "=== Building and analyzing master branch ==="
    echo "${reset} ${green} ${bold}======================================================${reset}"
    mvn -DskipTests clean install sonar:sonar \
	  -Dsonar.organization=$SONAR_ORGANIZATION \
	  -Dsonar.host.url=$SONAR_HOST_URL \
	  -Dsonar.login=$SONAR_TOKEN
  elif [ "$TRAVIS_BRANCH" == "release" ]; then
    mvn clean install sonar:sonar \
          -Prelease
	  -Dsonar.organization=$SONAR_ORGANIZATION \
	  -Dsonar.host.url=$SONAR_HOST_URL \
	  -Dsonar.login=$SONAR_TOKEN
  else
    mvn clean verify sonar:sonar \
	  -Dsonar.organization=$SONAR_ORGANIZATION \
	  -Dsonar.host.url=$SONAR_HOST_URL \
	  -Dsonar.login=$SONAR_TOKEN \
	  -Dsonar.branch.name=$TRAVIS_BRANCH
  fi
else
  echo "Yugerten Building and analyzing a pull request from $TRAVIS_PULL_REQUEST_BRANCH branch"
  
  mvn clean verify sonar:sonar \
    -Dsource.skip=true \
    -Dsonar.analysis.mode=preview \
    -Dsonar.organization=$SONAR_ORGANIZATION \
    -Dsonar.github.pullRequest=$TRAVIS_PULL_REQUEST \
    -Dsonar.github.repository=$TRAVIS_PULL_REQUEST_SLUG \
    -Dsonar.github.oauth=$SONAR_GITHUB_TOKEN \
    -Dsonar.host.url=$SONAR_HOST_URL \
    -Dsonar.login=$SONAR_TOKEN \
    -Dsonar.branch.name=$TRAVIS_PULL_REQUEST_BRANCH
fi
