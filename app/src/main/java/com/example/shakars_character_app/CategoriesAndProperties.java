package com.example.shakars_character_app;

public class CategoriesAndProperties {

    static class CategoriesAndPropertiesPC {
        int[] categoriesPC = {R.string.basic, R.string.looks, R.string.personalityTraits, R.string.relations, R.string.goalsAndBG};

        int[][] propertiesPC = {
                {R.string.name, R.string.titleKenningMoniker, R.string.race, R.string.gender, R.string.age, R.string.nationality, R.string.hometown, R.string.continent, R.string.rpClass, R.string.sexuality, R.string.traitsAttracted, R.string.occupation, R.string.religion},
                {R.string.eyes, R.string.skin, R.string.hair, R.string.scent, R.string.voice, R.string.characteristicFeatures},
                {R.string.personality, R.string.behaviourAndManners, R.string.characterTraits, R.string.characterFlaws, R.string.quirks, R.string.hobbies, R.string.strengthsAndWeaknesses, R.string.wantsAndDesires, R.string.fearsAndInsecurities, R.string.secrets},
                {R.string.family, R.string.alliesAndContacts,R.string.enemies, R.string.affiliatedGroups},
                {R.string.currentGoals,R.string.backgroundStory},
        };

        int [][] hintsPC = {
                {R.string.nameSuggestion, R.string.titleKenningMonikerSuggestion, R.string.raceSuggestion, R.string.genderSuggestion, R.string.ageSuggestion, R.string.nationalitySuggestion, R.string.hometownSuggestion, R.string.continentSuggestion, R.string.rpClassSuggestion, R.string.sexualitySuggestion, R.string.traitsAttractedSuggestion, R.string.occupationSuggestion, R.string.religionSuggestion},
                {R.string.eyesSuggestion, R.string.skinSuggestion, R.string.hairSuggestion, R.string.scentSuggestion, R.string.voiceSuggestion, R.string.characteristicFeaturesSuggestions},
                {R.string.personalitySuggestion, R.string.behaviourAndMannersSuggestion, R.string.characterTraitsSuggestions, R.string.characterFlawsSuggestions, R.string.quirksSuggestion, R.string.hobbiesSuggestions, R.string.strengthsAndWeaknessesSuggestions, R.string.wantsAndDesiresSuggestions, R.string.fearsAndInsecuritiesSuggestions, R.string.secretsSuggestion},
                {R.string.familySuggestions, R.string.alliesAndContactsSuggestions, R.string.enemiesSuggestions, R.string.affiliatedGroupsSuggestions},
                {R.string.currentGoalsSuggestions,R.string.backgroundStorySuggestions},

        };
    }

    static class CategoriesAndPropertiesNPC {
        int[] categoriesNPC = {R.string.basic, R.string.looks, R.string.personalityTraits, R.string.relations, R.string.goalsAndBG, R.string.npcDescriptions};

        int[][] propertiesNPC = {
                {R.string.name, R.string.titleKenningMoniker, R.string.race, R.string.gender, R.string.age, R.string.nationality, R.string.hometown, R.string.continent, R.string.rpClass, R.string.sexuality, R.string.traitsAttracted, R.string.occupation, R.string.religion},
                {R.string.eyes, R.string.hair, R.string.scent, R.string.voice, R.string.characteristicFeatures},
                {R.string.personality, R.string.behaviourAndManners, R.string.characterFlaws, R.string.quirks, R.string.hobbies, R.string.strengthsAndWeaknesses, R.string.wantsAndDesires, R.string.fearsAndInsecurities, R.string.secrets},
                {R.string.family, R.string.alliesAndContacts,R.string.enemies, R.string.affiliatedGroups},
                {R.string.currentGoals,R.string.backgroundStory},
                {R.string.specialAbilities, R.string.partOfWorld, R.string.hooksToPlayers, R.string.plotHooksSuggestions, R.string.motivations}
        };

        int [][] hintsNPC = {
                {R.string.nameSuggestion, R.string.titleKenningMonikerSuggestion, R.string.raceSuggestion, R.string.genderSuggestion, R.string.ageSuggestion, R.string.nationalitySuggestion, R.string.hometownSuggestion, R.string.continentSuggestion, R.string.rpClassSuggestion, R.string.sexualitySuggestion, R.string.traitsAttractedSuggestion,R.string.occupationSuggestion, R.string.religionSuggestion},
                {R.string.eyesSuggestion, R.string.hairSuggestion, R.string.scentSuggestion, R.string.voiceSuggestion, R.string.characteristicFeaturesSuggestions},
                {R.string.personalitySuggestion, R.string.behaviourAndMannersSuggestion, R.string.characterFlawsSuggestions, R.string.quirksSuggestion, R.string.hobbiesSuggestions, R.string.strengthsAndWeaknessesSuggestions, R.string.wantsAndDesiresSuggestions, R.string.fearsAndInsecuritiesSuggestions, R.string.secretsSuggestion},
                {R.string.familySuggestions, R.string.alliesAndContactsSuggestions, R.string.enemiesSuggestions, R.string.affiliatedGroupsSuggestions},
                {R.string.currentGoalsSuggestions,R.string.backgroundStorySuggestions},
                {R.string.specialAbilitiesSuggestions, R.string.partOfWorldSuggestion, R.string.hooksToPlayersSuggestions, R.string.plotHooksSuggestions, R.string.motivationsSuggestions}

        };
    }

    static CategoriesAndPropertiesPC dataPC = new CategoriesAndPropertiesPC();
    static CategoriesAndPropertiesNPC dataNPC = new CategoriesAndPropertiesNPC();
    static boolean[] foldedPC = new boolean[dataPC.categoriesPC.length];
    static boolean[] foldedNPC = new boolean[dataNPC.categoriesNPC.length];
}
