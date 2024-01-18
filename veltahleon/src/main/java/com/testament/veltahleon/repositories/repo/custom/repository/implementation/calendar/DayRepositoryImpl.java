package com.testament.veltahleon.repositories.repo.custom.repository.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.repo.custom.repository.ifc.calendar.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DayRepositoryImpl implements DayRepository<Day> {

    @Autowired
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Collection<Day> getDays() {
        return null;
    }

    @Override
    public Collection<Day> getDays(int pageNumber, int numberOfRecords) {
        return null;
    }

    @Override
    public Day getDayByID(Long id) {
        return null;
    }

    @Override
    public Boolean deleteDayByID(Long id) {
        return null;
    }

    @Override
    public Day saveDay(Day day) {
        KeyHolder holder = new GeneratedKeyHolder();
        return jdbc.queryForObject("<insert sql query here>", Map.of("day", day), Day.class);
    }

    @Override
    public Day updateDay(Day day) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", day.getName())
                .addValue("number", day.getDayNumber())
                .addValue("language", day.getLanguage())
                .addValue("description", day.getDescription());
        jdbc.update("sql query", parameters);
        return null;
    }
}
