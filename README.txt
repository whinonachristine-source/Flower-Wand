FLOWER WAND MOD  (Minecraft Java 1.21.1, Fabric)
=================================================

WHAT IT DOES
- Right-click a grass block with the Flower Wand.
- Flowers grow on grass in a 3-block radius around the spot you click.
- Pink cherry-blossom particles, little hearts and an amethyst chime play.
- 1 second cooldown between uses.

RECIPE (shaped crafting table, diagonal)
    . . A        A = Amethyst Shard
    . G .        G = Gold Ingot
    S . .        S = Stick

------------------------------------------------------------
STEP 1 - GET THE MOD FILE BUILT (free, no coding)
------------------------------------------------------------
1. Make a free account at https://github.com
2. Click "+" (top right) > "New repository". Name it "flowerwand".
   Choose Public. Click "Create repository".
3. Click "uploading an existing file". Drag in EVERYTHING inside this
   folder EXCEPT the ".github" folder and the BUILD_WORKFLOW.txt file:
       build.gradle, gradle.properties, settings.gradle, README.txt,
       and the "src" folder.
   Click "Commit changes".
4. Click "Add file" > "Create new file". In the name box type exactly:
       .github/workflows/build.yml
   (typing the "/" makes the folders). Open BUILD_WORKFLOW.txt, copy
   everything, paste it into the big box. Click "Commit changes".
5. Click the "Actions" tab. Wait 2-5 minutes for the build to get a
   green check mark. (Yellow = still working.)
6. Click the finished run, scroll to "Artifacts", click "flowerwand-mod"
   to download a zip. Inside is flowerwand-1.0.0.jar - that is your mod.

If the run shows a red X, click it, copy the error text and send it to
Claude. It can be fixed.

------------------------------------------------------------
STEP 2 - INSTALL IN MINECRAFT
------------------------------------------------------------
1. Install Fabric Loader for 1.21.1: https://fabricmc.net/use/installer/
2. Download Fabric API for 1.21.1 (put it in mods too):
   https://modrinth.com/mod/fabric-api
3. Put flowerwand-1.0.0.jar AND the Fabric API jar into your
   .minecraft/mods folder.
4. Start Minecraft using the Fabric profile. Needs Java 21 (the
   default launcher already includes it for 1.21.1).
