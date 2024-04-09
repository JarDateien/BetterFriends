package de.jardateien.betterfriends.core;

import de.jardateien.betterfriends.core.utils.Notification;
import net.labymod.api.Laby;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import net.labymod.api.labyconnect.protocol.model.request.OutgoingFriendRequest;
import net.labymod.api.util.MethodOrder;

@ConfigName("settings")
public class BetterFriendsConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  @MethodOrder(after = "enabled")
  @ButtonSetting
  public void acceptAll(Setting setting) {
    var connect = Laby.labyAPI().labyConnect();
    if (!connect.isAuthenticated()) return;

    var session = connect.getSession();
    if (session == null) return;
    if(!session.isAuthenticated()) return;

    var requests = session.getIncomingRequests();
    if(requests.isEmpty()) return;

    requests.forEach(IncomingFriendRequest::accept);
  }

  @MethodOrder(after = "acceptAll")
  @ButtonSetting
  public void declineAll(Setting setting) { //session.getOutgoingRequests().forEach(OutgoingFriendRequest::withdraw);
    var connect = Laby.labyAPI().labyConnect();
    if (!connect.isAuthenticated()) return;

    var session = connect.getSession();
    if (session == null) return;
    if(!session.isAuthenticated()) return;

    var requests = session.getIncomingRequests();
    if(requests.isEmpty()) return;

    requests.forEach(IncomingFriendRequest::decline);
  }

  @MethodOrder(after = "declineAll")
  @ButtonSetting
  public void removeAll(Setting setting) {
    var connect = Laby.labyAPI().labyConnect();
    if (!connect.isAuthenticated()) return;

    var session = connect.getSession();
    if (session == null) return;
    if(!session.isAuthenticated()) return;

    var requests = session.getOutgoingRequests();
    requests.forEach(OutgoingFriendRequest::withdraw);
    Notification.advancement(null, "Removed", "Fertig " + requests.size());
  }
}
