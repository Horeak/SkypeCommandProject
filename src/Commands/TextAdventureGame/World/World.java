package Commands.TextAdventureGame.World;

import Commands.TextAdventureGame.Event;
import Commands.TextAdventureGame.Item;
import Commands.TextAdventureGame.Player;
import Commands.TextAdventureGame.Text.TextAction;
import Commands.TextAdventureGame.Text.TextAdventureCommand;

public class World {

	public static WorldType[] worldTypes = new WorldType[]{
			new WorldType("Rocky hills", new TextAction[]{
						new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print(player.name + " Tried climbing but fell and hurt themselves.");
							player.health -= 2;
						}

						@Override
						public String getName() {
							return "Try climbing";
						}
					},
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							if(TextAdventureCommand.rand.nextInt(2) == 1) {
								int i = TextAdventureCommand.rand.nextInt(3) + 1;

								player.print(player.name + " found " + i + " rock(s)!");

								for(int g = 0; g < i; g++)
								player.items.add(new Item("Rock", "A small rock"));
							}else{
								player.print(player.name + " did not find any rocks.");
							}
						}

						@Override
						public String getName() {
							return "Gather rocks";
						}
					},
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print("You see large rocky hills. They do not seem climbable. The ground is littered with small rocks. ");
							//TODO Possible for enemies to appear when looking around
						}

						@Override
						public String getName() {
							return "Look around";
						}
					},

					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.world = new World();
							player.event = new Event(player.world.worldType.actions, player.name + " walked and found " + player.world.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")", player.chat);
							//TODO Make walking have a chance of spawning enemies
						}

						@Override
						public String getName() {
							return "Walk";
						}
					},

			}),


			new WorldType("Open plains", new TextAction[]{
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.world = new World();
							player.event = new Event(player.world.worldType.actions, player.name + " walked and found " + player.world.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")", player.chat);
							//TODO Make walking have a chance of spawning enemies
						}

						@Override
						public String getName() {
							return "Walk";
						}
					},
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print("You see a large open plain with no specific features in sight.");
						}

						@Override
						public String getName() {
							return "Look around";
						}
					},
			}),


			new WorldType("Forest", new TextAction[]{
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.world = new World();
							player.event = new Event(player.world.worldType.actions, player.name + " walked and found " + player.world.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")", player.chat);
							//TODO Make walking have a chance of spawning enemies
						}

						@Override
						public String getName() {
							return "Walk";
						}
					},

					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print("You see a large forest with many trees surrounding the area. Twigs litter the ground. ");
							//TODO Possible for enemies to appear when looking around
						}

						@Override
						public String getName() {
							return "Look around";
						}
					},

					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							if(TextAdventureCommand.rand.nextInt(2) == 1) {
								int i = TextAdventureCommand.rand.nextInt(3) + 1;

								player.print(player.name + " found " + i + " twig(s)!");

								for(int g = 0; g < i; g++)
									player.items.add(new Item("Twig", "A small twig. Might be useful later"));
							}else{
								player.print(player.name + " did not find any twigs.");
							}
						}

						@Override
						public String getName() {
							return "Gather twigs";
						}
					},
			}),


			new WorldType("Shore", new TextAction[]{
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.world = new World();
							player.event = new Event(player.world.worldType.actions, player.name + " walked and found " + player.world.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")", player.chat);
							//TODO Make walking have a chance of spawning enemies
						}

						@Override
						public String getName() {
							return "Walk";
						}
					},

					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print("You stop and look at the shore and watch the sea and the waves. You do not notice anything else.");
							//TODO Possible for enemies to appear when looking around
						}

						@Override
						public String getName() {
							return "Look around";
						}
					},
			}),

			new WorldType("Tundra", new TextAction[]{
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.world = new World();
							player.event = new Event(player.world.worldType.actions, player.name + " walked and found " + player.world.desc + ". What do you do? (" + Event.getActions(player.world.worldType.actions) + ")", player.chat);
							//TODO Make walking have a chance of spawning enemies
						}

						@Override
						public String getName() {
							return "Walk";
						}
					},
					new TextAction() {
						@Override
						public void performAction( Player player, String text ) throws Exception {
							player.print("You do not see anything special apart from snow and hills.");
							//TODO Possible for enemies to appear when looking around
						}

						@Override
						public String getName() {
							return "Look around";
						}
					},
			}),
	};

	public String desc;
	public WorldType worldType;

	public World(){
		worldType = worldTypes[ TextAdventureCommand.rand.nextInt(worldTypes.length)];
		desc = worldType.desc;
	}


}
