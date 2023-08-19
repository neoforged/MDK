package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

// Learn more about game tests at https://docs.neoforged.net/docs/misc/gametest

@GameTestHolder(ExampleMod.MODID)
@PrefixGameTestTemplate(false)
public class ExampleModGameTests {

    // You can create new templates by running `/test create` in game, building something, then hitting save
    // The saved structure will go to run/client/saves/{world}/generated/minecraft/structures
    // You can then copy this into your game test resources folder
    @GameTest(template="1x2x1")
    public static void gate_opens(GameTestHelper helper) {
        // This test sets itself up programmatically instead of relying on the template
        BlockPos gatePos = new BlockPos(0, 1, 0);
        helper.setBlock(gatePos, Blocks.OAK_FENCE_GATE);
        helper.assertBlockState(gatePos, state -> !state.getValue(FenceGateBlock.OPEN), () -> "Gate should start closed");
        Player player = helper.makeMockPlayer();
        BlockState gateBlockState = helper.getBlockState(gatePos);
        gateBlockState.getBlock().use(
                gateBlockState,
                helper.getLevel(),
                helper.absolutePos(gatePos),
                player,
                InteractionHand.MAIN_HAND,
                new BlockHitResult(
                        new Vec3(0.5, 0.5, 0.5),
                        Direction.NORTH,
                        helper.absolutePos(gatePos),
                        false
                )
        );
        helper.assertBlockState(gatePos, state -> state.getValue(FenceGateBlock.OPEN), () -> "Gate should be open");
        helper.succeed();
    }
}
