package thiengo.com.br.canalvinciusthiengo.config

enum class OneSignalConfig private constructor( val value: String ){
    FIREBASE_CM_SERVER_KEY( value = "AAAAM6VZ2kE:APA91bGaR_gveYgDHZ2WL9U5amxlBMtOPVTBrUolgUIYkXffk9WeTL3JZGTEFn715RGTD3eXd8N1LEWaByAaRguc1Fz5kV2HN82oehd6A6jceh_JpgayOoqEY6cs0ipEX59h6dj3D1tg" ),
    FIREBASE_CM_SENDER_ID( value = "221817461313" ),
    ONE_SIGNAL_APP_ID( value = "e4c1751c-0ce9-40f7-8590-f9daa7927539" ),

    NOTIFICATION_VIDEO_PARAMETER( value = "video" ),
    NOTIFICATION_TITLE_PARAMETER( value = "title" ),
    NOTIFICATION_DESCRIPTION_PARAMETER( value = "description" ),
    NOTIFICATION_EMPTY_PARAMETER( value = "" )
}