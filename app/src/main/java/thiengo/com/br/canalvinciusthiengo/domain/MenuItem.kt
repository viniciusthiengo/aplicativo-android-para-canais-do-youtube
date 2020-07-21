package thiengo.com.br.canalvinciusthiengo.domain

class MenuItem(
    val id: Int,
    val label: String,
    val icon: Int,
    var isSelected: MenuItemStatus = MenuItemStatus.NOT_SELECTED )