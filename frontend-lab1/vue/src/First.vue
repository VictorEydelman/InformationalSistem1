<template>
  <div>
    <input type="text" v-model="username">
    <input type="text" v-model="password">
    <h7 v-if="passwordstatus && password.length<5" style="color: red">fsdfs</h7>
    <button type="submit" @click="Reg">Регистрация</button>
    <button type="submit" @click="LogIn">Войти</button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      s:1,
      username: "",
      password: "",
      response: new Array(0),
      role: "ROLE_USER",
      passwordstatus:false
    }
  },
  methods: {
    Reg() {
      localStorage.setItem("jwt", "")
      if(this.password.length>=5) {
        fetch("http://localhost:1298/api/user/register", {
          method: "PUT",
          headers: new Headers({
            'Content-Type': 'application/json'
          }),
          body: JSON.stringify({
            username: this.username,
            password: this.password,
          })
        }).then(response => {
          if (!response.ok) {
            // Если ответ не успешный (например, статус 400), выбрасываем ошибку
            return response.json().then(err => {
              throw new Error(err[0]); // Предполагаем, что сообщение об ошибке находится в первом элементе массива
            });
          }
          return response.json();
        }).then(response => {
          this.response = response
          localStorage.setItem("jwt", this.response[1])
          localStorage.setItem("username", this.response[0])
          localStorage.setItem("role", this.response[2])
          alert(localStorage.getItem('role'))
          this.$router.push({name: 'main'})

        }).catch(error => alert("Пользователь с таким логином существует"))
      } else {
        this.passwordstatus=true
      }
    },
    LogIn() {
      if (this.password.length >= 5) {
        fetch("http://localhost:1298/api/user/avtorization", {
          method: "POST",
          headers: new Headers({
            'Content-Type': 'application/json'
          }),
          body: JSON.stringify({
            username: this.username,
            password: this.password,
            role: this.role
          })
        }).then(response => {
          return response.json();
        }).then((response) => {
          this.response = response
          localStorage.setItem("jwt", this.response[1])
          localStorage.setItem("username", this.response[0])
          localStorage.setItem("role", this.response[2])
          alert(this.response[2])
          this.$router.push({name: 'main'});
        }).catch(error => alert("Пользователя с таким логином и паролем не существует"))
      }else {
        this.passwordstatus=true
      }
    }
  }
}
</script>

<style scoped>

</style>
