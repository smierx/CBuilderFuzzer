package CBuilder.objects;

import CBuilder.Expression;
import CBuilder.Reference;
import CBuilder.Statement;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SuperCall extends Call {

    // addAll does not return `this`,
    // and `super()` needs to be called first...
    // so we need an extra method simply to be able to have a default element in the front of the list....
    private static <T> List<T> chainedAddAll(List<T> thisInstance, List<T> appended) {
        // thisInstance may very well be immutable (who needs rust's ownership model anyway)...
        List<T> result = new LinkedList<>();
        result.addAll(thisInstance);
        result.addAll(appended);
        return result;
    }

    /**
     * @param args     The positional arguments of the parent class constructor.
     * @param kwargs   The keyword arguments of the parent class constructor.
     */
    public SuperCall(List<Expression> args,
                     Map<String, Expression> kwargs) {
        super(new Reference("__mpy_super"), chainedAddAll(List.of(new Reference("self")), args), kwargs);
    }
}