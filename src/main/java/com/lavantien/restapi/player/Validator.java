package com.lavantien.restapi.player;

import com.lavantien.restapi.player.Validator.ValidationResult;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public interface Validator extends Function<Player, ValidationResult> {
  String VALID_EMAIL_TOKEN = "@";
  Integer VALID_AGE = 20;

  static Validator validEmail() {
    return player -> {
      if (player.getEmail().contains(VALID_EMAIL_TOKEN)) {
        return ValidationResult.SUCCESS;
      }
      return ValidationResult.EMAIL_NOT_VALID;
    };
  }

  static Validator validAge() {
    return player -> {
      if (Period.between(player.getDateOfBirth(), LocalDate.now()).getYears() >=
          VALID_AGE) {
        return ValidationResult.SUCCESS;
      }
      return ValidationResult.NOT_AN_ADULT;
    };
  }

  default Validator and(Validator other) {
    return player -> {
      var result = this.apply(player);
      if (result == ValidationResult.SUCCESS) {
        return other.apply(player);
      }
      return result;
    };
  }

  enum ValidationResult { SUCCESS, EMAIL_NOT_VALID, NOT_AN_ADULT }
}
