package thiengo.com.br.canalvinciusthiengo.config

/**
 * Classe que contém todos os dados estáticos de
 * configuração de notificações push no sistema
 * via API OneSignal.
 *
 * As classes internas (Firebase, App e Notification)
 * e também os rótulos de todos os companion object.
 * Estes estão presentes em código somente para
 * facilitar a leitura dele. Ou seja, em termos de
 * regras de sintaxe esses não são obrigatórios.
 */
class OneSignalConfig {

    class Firebase {
        companion object CloudMessage {
            /**
             * Constantes com dados de configuração
             * do Firebase Cloud Message. Dados que
             * devem ser colocados no dashboard do
             * OneSignal.
             */
            const val SERVER_KEY = ""
            const val SENDER_ID = ""
        }
    }

    class App {
        companion object {
            /**
             * Constante com dado de configuração
             * do OneSignal em aplicativo. Esse dado
             * entra na variável onesignal_app_id do
             * Gradle Nível de Aplicativo, ou
             * build.gradle (Module: app).
             */
            const val ID = ""
        }
    }

    class Notification {
        companion object Parameter {
            /**
             * Constantes com definições de campos
             * de JSON para acesso aos dados
             * enviados ao aplicativo via
             * notificação push OneSignal.
             */
            const val VIDEO = "video"
            const val TITLE = "title"
            const val DESCRIPTION = "description"

            /**
             * Constante com uma definição de
             * String vazia para retornos onde
             * dados opcionais (como o dado de
             * DESCRIPTION) não foram fornecidos
             * em notificação push.
             */
            const val EMPTY = ""
        }
    }
}