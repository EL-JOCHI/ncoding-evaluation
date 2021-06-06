import { ActionTree } from 'vuex';
import {User} from 'src/models/User';
import {api} from 'boot/axios';

const actions: ActionTree<User, any> = {
  doLogin (context, payload) {
    const localVarPath = '/users/login/';
    return new Promise((resolve, reject) => {
      api.post(localVarPath, payload).then((response) => {
        context.commit('setUser', response.data);
        resolve('/');
      }).catch(() => {
        console.log('An error occurred...');
        reject('Password And/Or User does not exists.');
      });
    });
  }
};

export default actions;
