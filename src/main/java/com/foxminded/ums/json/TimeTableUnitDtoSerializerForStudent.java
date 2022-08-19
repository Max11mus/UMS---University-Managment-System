package com.foxminded.ums.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.foxminded.ums.dto.TimeTableUnitDto;

import java.io.IOException;
import java.util.List;

public class TimeTableUnitDtoSerializerForStudent extends StdSerializer<List<TimeTableUnitDto>> {
    public TimeTableUnitDtoSerializerForStudent(Class t) {
        super(t);
    }

    @Override
    public void serialize(List<TimeTableUnitDto> list, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();

        for(TimeTableUnitDto tableUnitDto : list ) {
            gen.writeStartObject();

            gen.writeStringField("id", tableUnitDto.getId().toString());
            gen.writeStringField("begin", tableUnitDto.getBegin().toString());
            gen.writeStringField("end", tableUnitDto.getEnd().toString());

            gen.writeObjectFieldStart("location");
            gen.writeStringField("address", tableUnitDto.getLocation().getAddress());
            gen.writeEndObject();

            gen.writeObjectFieldStart("teacher");
            gen.writeStringField("name", tableUnitDto.getLecture().getTeacher().getName());
            gen.writeStringField("surname", tableUnitDto.getLecture().getTeacher().getSurname());
            gen.writeEndObject();

            gen.writeObjectFieldStart("lecture");
            gen.writeStringField("id", tableUnitDto.getLecture().getId().toString());
            gen.writeEndObject();

            gen.writeEndObject();
        }

        gen.writeEndArray();
    }
}
