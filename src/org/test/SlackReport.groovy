class SlackReport implements Report {

    private String token
    private String slackChannel

    SlackReport(String token, String slackDomain, String slackChannel) {
        this.token = token
        this.slackChannel = slackChannel
    }

    def getUserByEmail(String email) {
        def author = sh(
                script: 'curl https://slack.com/api/users.lookupByEmail\\?token\\=' + token +
                        '\\&email\\=' + email + ' | sed -n \'s|.*"id":"\\([^"]*\\)".*|\\1|p\'',
                returnStdout: true
        )

        return author
    }

    def sendMessage(String title, String message, String color) {
        response = slackSend(channel: "${slackChannel}",
                teamDomain: "${slackDomain}",
                token: "${token}",
                botUser: true,
                message: "${title}",
                attachments: [
                        [
                                color: "${color}",
                                text : "${message}"
                        ]
                ])
        println "Response: ${response}"
    }
}

