class PlayStoreDistribution implements Distribution {

    def upload(String aabPath) {
        def packageName = sh(script: "java -jar ~/bundletool.jar dump manifest --bundle $aabPath --xpath /manifest/@package", returnStdout: true)

        sh 'bundle install'
        sh "bundle exec fastlane supply --aab $aabPath --skip_upload_apk true --track internal --package_name $packageName"
    }
}
