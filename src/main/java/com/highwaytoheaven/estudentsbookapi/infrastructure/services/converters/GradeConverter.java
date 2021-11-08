package com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class GradeConverter {

  private final Map<GradeType, Converter> converters;

  {
    converters = HashMap.of(
        GradeType.LETTER, new LetterConverter(),
        GradeType.NUMERIC, new NumericConverter(),
        GradeType.PERCENTAGE, new PercentConverter()
    );
  }

  public Double convertValueToType(String s, GradeType type) {
    Option<Converter> converter = this.converters.get(type);

    if (converter.isEmpty()) {
      throw new IllegalStateException("Not defined grade");
    }

    return converter.get().convert(s);
  }

  public interface Converter {

    Double convert(String s);
  }

  public class LetterConverter implements Converter {

    @Override
    public Double convert(String s) {
      return null;
    }
  }

  private class NumericConverter implements Converter {

    @Override
    public Double convert(String s) {
      return null;
    }
  }

  private class PercentConverter implements Converter {

    @Override
    public Double convert(String s) {
      return null;
    }
  }
}
