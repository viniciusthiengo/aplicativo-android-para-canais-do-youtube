package thiengo.com.br.canalvinciusthiengo.model

/**
 * Um item do menu principal, que fica ao fundo
 * do aplicativo. Bottom menu.
 *
 * O objetivo desta classe (objetos desta classe)
 * é manter os dados de identificação do item de
 * menu principal, incluindo identificação do
 * estado atual dele (selecionado ou não).
 *
 * @property id identificador único do item.
 * @property label rótulo do item.
 * @property icon ícone que ajuda a identificar
 * o item.
 * @property isSelected estado do item.
 * @constructor cria um objeto completo do tipo
 * MenuItem.
 */
class MenuItem(
    val id: Int,
    val label: String,
    val icon: Int,
    var isSelected: MenuItemStatus = MenuItemStatus.NOT_SELECTED )