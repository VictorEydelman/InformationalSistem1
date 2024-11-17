<template>
  <div id="first">
    <div style="margin-left: 40%; margin-right: 40%; padding-top: 15%">
      <table border="1" style="padding-left: 10%; background-color: #f0f1ff">
        <p style="color: #51f551">Имя</p>
        <input type="text" v-model="username">
        <p style="color: #51f551">Пароль</p>
        <input type="text" v-model="password">
        <h7 v-if="passwordstatus && password.length<5" style="color: red">Неверно введённые данные</h7>
        <button style="margin-top: 10%" type="submit" @click="Reg">Регистрация</button>
        <button style="margin-left: 5%" type="submit" @click="LogIn">Войти</button>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name:"first",
  data () {
    return {
      s:1,
      username: "",
      password: "",
      response: new Array(0),
      role: "ROLE_USER",
      passwordstatus:false,
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
table{
  border-radius: 1px;
  box-shadow: 0 0 0 4px #968089, 1px 1px 1px 1px rgba(150, 128, 137, 0.5);
}
#first {
  margin-top: 0;
  height: 100vh;
  background-image: url('./assets/images/c2150453d059e8999c5f0b211ce334f7c869147c.jpg'); /* Убедитесь, что путь правильный */
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
</style>
