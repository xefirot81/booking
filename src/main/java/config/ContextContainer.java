package config;

import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;

public class ContextContainer {

    @Autowired
    @Delegate
    private Context context;

}
