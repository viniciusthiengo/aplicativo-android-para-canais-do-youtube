package thiengo.com.br.canalvinciusthiengo.ui.fragment

/**
 * Contém os possíveis estados da UI dos fragmentos
 * que têm dados carregados de maneira assíncrona.
 */
enum class UiFragLoadDataStatus {
    LOADING,
    LOADED,
    NO_MAIN_CONTENT
}