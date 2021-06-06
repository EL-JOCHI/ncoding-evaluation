<template>
  <q-page class="bg-dynamic window-height window-width row justify-center items-center">
    <div class="column">
      <div class="row">
        <h5 class="text-h5 text-white q-my-md">Student's App - NCoding</h5>
      </div>
      <div class="row">
        <q-card square bordered class="q-pa-lg shadow-2 glass-panel">
          <q-card-section>
            <q-form class="q-gutter-md">
              <q-input square filled clearable
                       v-model.trim="formData.user.email"
                       type="email"
                       label="Email">
                <template v-slot:prepend>
                  <q-icon name="email"/>
                </template>
              </q-input>
              <q-input square filled clearable v-model="formData.user.password" type="password" label="Password">
                <template v-slot:prepend>
                  <q-icon name="lock"/>
                </template>
              </q-input>
            </q-form>
          </q-card-section>
          <q-card-actions class="q-px-md">
            <q-btn v-if="!formData.registrationMode" unelevated color="primary" size="lg" class="full-width"
                   label="Login" @click="logIn"/>
            <q-btn v-if="formData.registrationMode" unelevated color="accent" size="lg" class="full-width"
                   label="Create Account" @click="createAccount"/>
          </q-card-actions>
          <q-card-section class="text-center q-pa-sm-none">
            <q-btn @click="toggleMode" color="accent">{{ formData.registrationText }}</q-btn>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import {UserRequest} from '../models/UserRequest';
import {ref} from 'vue';
import {useStore} from 'src/store';
import {useRouter} from 'vue-router';
import {useQuasar} from 'quasar';

export default {
  name: "Login",
  setup() {
    const store = useStore();
    const router = useRouter();
    const q = useQuasar();

    const formData = ref({
      user: <UserRequest>{email: '', password: ''},
      registrationMode: false,
      registrationText: 'Create Account'
    });

    const submitForm = (() => {
      console.log('submitForm -> ' + formData.value.user.email + "," + formData.value.user.password);
    });

    const logIn = (() => {
      store.dispatch("user/doLogin", formData.value.user).then(path => {
        console.log("Redirecting to: "+ JSON.stringify(path));
        router.push(path);
      }).catch(()=>{
        q.dialog({
          title: 'Password/Email does not exists.',
          message: 'Please try again...'
        })
      });
    })

    const toggleMode = (() => {
      formData.value.registrationMode = !formData.value.registrationMode;
      formData.value.registrationText = !formData.value.registrationMode ? 'Create Account' : 'Sign in';
      console.log(formData.value.registrationMode + ' -> ' + formData.value.user.email + "," + formData.value.user.password);
    });

    const createAccount = (() => {
      console.log('Creating Account with: ' + formData.value.user.email + "," + formData.value.user.password);
    });

    return {
      formData,
      submitForm,
      logIn,
      createAccount,
      toggleMode
    }
  }
}
</script>

<style scoped lang="sass">
.q-card
  width: 360px

.bg-dynamic
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab)
  background-size: 400% 400%
  animation: gradient 15s ease infinite

@keyframes gradient
  0%
    background-position: 0% 50%

  50%
    background-position: 100% 50%

  100%
    background-position: 0% 50%

.glass-panel
  background: rgba( 255, 255, 255, 0.50 )
  box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.4 )
  backdrop-filter: blur( 10.0px )
  -webkit-backdrop-filter: blur( 10.0px )
  border-radius: 10px
  border: 1px solid rgba( 255, 255, 255, 0.2 )
  color: white


</style>
