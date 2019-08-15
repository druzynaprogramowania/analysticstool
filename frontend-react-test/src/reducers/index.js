import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import itemReducer from "./itemReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
  errors: errorReducer,
  item: itemReducer,
  security: securityReducer
});
