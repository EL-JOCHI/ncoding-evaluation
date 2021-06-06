import { MutationTree } from 'vuex';
import {User} from 'src/models/User';

const mutation: MutationTree<User> = {
  setUser (state: User, payload: User) {
    state = payload;
  }
};

export default mutation;
