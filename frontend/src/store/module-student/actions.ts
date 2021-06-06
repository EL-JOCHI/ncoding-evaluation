import {ActionTree, useStore} from 'vuex';
import {StateInterface} from '../index';
import {Student} from 'src/models/Student';
import {api} from 'boot/axios';
import {useQuasar} from 'quasar';

const actions: ActionTree<Student, StateInterface> = {
  fetchStudentData(context) {
    const store = useStore();
    const q = useQuasar();
    const localVarPath = '/students/user/{id}'
      .replace('{${"id"}}', encodeURIComponent(String(1)));
    api.get(localVarPath)
      .then((response) => {
        context.commit('setStudent', response.data)
      })
      .catch(() => {
      q.notify({
        color: 'negative',
        position: 'top',
        message: 'Loading failed',
        icon: 'report_problem'
      })
    })
  }
};

export default actions;
