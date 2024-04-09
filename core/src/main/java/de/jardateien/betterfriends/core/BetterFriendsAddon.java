package de.jardateien.betterfriends.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class BetterFriendsAddon extends LabyAddon<BetterFriendsConfiguration> {

  private static BetterFriendsAddon instance;
  public static BetterFriendsAddon instance() { return instance; }

  @Override
  protected void enable() {
    instance = this;
    this.registerSettingCategory();

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<BetterFriendsConfiguration> configurationClass() {
    return BetterFriendsConfiguration.class;
  }
}
