package Base;

public class BaseSteps {
    public BaseUtils base;
    public FuncModules func;
    public DataStore data;
    public BaseSteps(BaseUtils base, DataStore data) {
        this.base = base;
        this.data = data;
        this.func = new FuncModules(base.driver);
    }
}
