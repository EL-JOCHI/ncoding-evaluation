import { GetterTree } from 'vuex';
import {User} from 'src/models/User';

const getters: GetterTree<User, any> = {
  someAction (/* context */) {
    // your code
  }
};

export default getters;
