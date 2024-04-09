package de.jardateien.betterfriends.core.utils;

import de.jardateien.betterfriends.core.BetterFriendsAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.notification.Notification.Type;
import java.util.UUID;

public class Notification {

  public static void advancement(UUID head, String title, String message) {
    net.labymod.api.notification.Notification.Builder builder = net.labymod.api.notification.Notification.builder()
        .title(Component.text(title.isEmpty() ? title : Laby.labyAPI().getName()))
        .icon(Icon.head(head != null ? head : Laby.labyAPI().getUniqueId()))
        .text(Component.text(message))
        .type(Type.SOCIAL);
    Laby.labyAPI().notificationController().push(builder.build());
  }

  public static void chat(String message) {
    BetterFriendsAddon.instance().displayMessage(message);
  }

}
