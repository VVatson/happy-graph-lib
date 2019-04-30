package com.kumaev.graph.exception;

public class YouMadeGraphLibSadException extends IllegalArgumentException {

    private String sadnessCode;

    public YouMadeGraphLibSadException(String message, String sadnessCode) {
        super(message);
        this.sadnessCode = sadnessCode;
    }

    public String howToMakeGraphLibHappy() {
        return SadnessExceptionConstants.getHappinessRecipe(sadnessCode);
    }
}
