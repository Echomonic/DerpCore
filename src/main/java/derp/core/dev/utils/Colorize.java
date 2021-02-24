package derp.core.dev.utils;

import net.minecraft.server.v1_8_R3.ChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Colorize {

	public static void color(String message, CommandSender sender){

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	public static void actionBar(String text, Player player){
		PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', text) + "\"}"), (byte) 2);

		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
	public static void sendTitle(String text,String subtitle, Player player){
		PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', text) + "\"}"));
		PacketPlayOutTitle subPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}"));
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(subPacket);

	}
}
