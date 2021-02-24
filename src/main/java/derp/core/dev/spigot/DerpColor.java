package derp.core.dev.spigot;

import org.bukkit.ChatColor;

public enum DerpColor {

	BLACK(ChatColor.BLACK),
	DARK_BLUE(ChatColor.DARK_BLUE),
	DARK_GREEN(ChatColor.DARK_GREEN),
	DARK_AQUA(ChatColor.DARK_AQUA),
	DARK_RED(ChatColor.DARK_RED),
	PURPLE(ChatColor.DARK_PURPLE),
	ORANGE(ChatColor.GOLD),
	GRAY(ChatColor.GRAY),
	DARK_GRAY(ChatColor.DARK_GRAY),
	BLUE(ChatColor.BLUE),
	GREEN(ChatColor.GREEN),
	CYAN(ChatColor.AQUA),
	PINK(ChatColor.LIGHT_PURPLE),
	MAGENTA(ChatColor.LIGHT_PURPLE),
	YELLOW(ChatColor.YELLOW),
	WHITE(ChatColor.WHITE),
	MAGIC(ChatColor.MAGIC),
	BOLD(ChatColor.BOLD),
	TILTED(ChatColor.ITALIC),
	RESET(ChatColor.RESET),
	;

	private ChatColor color;

	DerpColor(ChatColor color) {
		this.color = color;
	}

	public ChatColor toBold(){


		return DerpColor.BOLD.getColor();
	}


	public ChatColor getColor() {
		return color;
	}

}
