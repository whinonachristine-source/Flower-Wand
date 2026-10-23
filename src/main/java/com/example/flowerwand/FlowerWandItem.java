package com.example.flowerwand;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlowerWandItem extends Item {
	private static final int RADIUS = 3;
	private static final int COOLDOWN_TICKS = 20; // 1 second
	private static final float FLOWER_CHANCE = 0.4f;

	private static final Block[] FLOWERS = new Block[] {
			Blocks.DANDELION,
			Blocks.POPPY,
			Blocks.BLUE_ORCHID,
			Blocks.ALLIUM,
			Blocks.AZURE_BLUET,
			Blocks.RED_TULIP,
			Blocks.ORANGE_TULIP,
			Blocks.WHITE_TULIP,
			Blocks.PINK_TULIP,
			Blocks.OXEYE_DAISY,
			Blocks.CORNFLOWER,
			Blocks.LILY_OF_THE_VALLEY
	};

	public FlowerWandItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();

		// Only do the real work on the server side
		if (!(world instanceof ServerWorld serverWorld)) {
			return ActionResult.SUCCESS;
		}

		BlockPos center = context.getBlockPos();
		PlayerEntity player = context.getPlayer();

		for (int dx = -RADIUS; dx <= RADIUS; dx++) {
			for (int dz = -RADIUS; dz <= RADIUS; dz++) {
				// Keep it a round area
				if (dx * dx + dz * dz > RADIUS * RADIUS) {
					continue;
				}

				// Look a little up and down so it works on uneven ground
				for (int dy = -1; dy <= 1; dy++) {
					BlockPos groundPos = center.add(dx, dy, dz);
					BlockPos abovePos = groundPos.up();

					if (!world.getBlockState(groundPos).isOf(Blocks.GRASS_BLOCK)) {
						continue;
					}
					if (!world.getBlockState(abovePos).isAir()) {
						continue;
					}
					if (world.getRandom().nextFloat() > FLOWER_CHANCE) {
						continue;
					}

					Block flower = FLOWERS[world.getRandom().nextInt(FLOWERS.length)];
					BlockState flowerState = flower.getDefaultState();

					if (flowerState.canPlaceAt(world, abovePos)) {
						world.setBlockState(abovePos, flowerState);

						// A little pink sparkle on each new flower
						serverWorld.spawnParticles(ParticleTypes.CHERRY_LEAVES,
								abovePos.getX() + 0.5, abovePos.getY() + 0.5, abovePos.getZ() + 0.5,
								4, 0.25, 0.25, 0.25, 0.02);
					}
					break; // only one flower per column
				}
			}
		}

		// Big pink burst around the clicked spot
		double cx = center.getX() + 0.5;
		double cy = center.getY() + 1.2;
		double cz = center.getZ() + 0.5;
		serverWorld.spawnParticles(ParticleTypes.CHERRY_LEAVES, cx, cy, cz, 40, 2.0, 0.6, 2.0, 0.05);
		serverWorld.spawnParticles(ParticleTypes.HEART, cx, cy + 0.3, cz, 8, 1.5, 0.5, 1.5, 0.02);

		world.playSound(null, center, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1.0f, 1.2f);

		if (player != null) {
			player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
		}

		return ActionResult.SUCCESS;
	}
}
