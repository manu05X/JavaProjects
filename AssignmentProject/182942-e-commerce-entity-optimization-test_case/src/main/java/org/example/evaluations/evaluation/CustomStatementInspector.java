package org.example.evaluations.evaluation;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class CustomStatementInspector implements StatementInspector {

    private static final ThreadLocal<List<String>> executedQueries = ThreadLocal.withInitial(ArrayList::new);


    @Override
    public String inspect(String sql) {
        executedQueries.get().add(sql);
        return sql;
    }

    public List<String> getExecutedQueries() {
        return executedQueries.get();
    }

    public void clear() {
        executedQueries.get().clear();
    }
}
