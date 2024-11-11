<script>
export default {
  data() {
    return {
      dots: new Array(0),
      description:""
    }
  },
  methods: {
    filter() {
      fetch(`http://localhost:1298/api/filter/getByDescription/${this.description}`, {
        method: "GET",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(request =>
      {
        return request.json()
      }).then(request =>
        {
          this.dots=request
        }
      )
    },
    close(){
      this.$router.push({name: 'main'});
    }
  },
}
</script>

<template>
  <div>
    <div>
      <button type="submit" @click="close">Закрыть вкладку</button>
    </div>
    <div>
      <input type="text" @input="filter" v-model="description">
    </div>
    <table>
      <thead>
      <th>name</th>
      <th>coordinates_x</th>
      <th>coordinates_y</th>
      <th>creationDate</th>
      <th>description</th>
      <th>difficulty</th>
      <th>discipline_name</th>
      <th>discipline_lectureHours</th>
      <th>minimalPoint</th>
      <th>averagePoint</th>
      <th>person_name</th>
      <th>person_eyeColor</th>
      <th>person_hairColor</th>
      <th>person_location_x</th>
      <th>person_location_y</th>
      <th>person_location_z</th>
      <th>person_weight</th>
      <th>person_nationality</th>
      <th>permission</th>
      </thead>
      <tbody>
      <tr v-for="dot in dots" :key="dot">
        <td>{{dot.id}}</td>
        <td>{{dot.name}}</td>
        <td>{{dot.coordinates.x}}</td>
        <td>{{dot.coordinates.y}}</td>
        <td>{{dot.creationDate}}</td>
        <td>{{dot.description}}</td>
        <td>{{dot.difficulty}}</td>
        <td>{{dot.discipline.name}}</td>
        <td>{{dot.discipline.lectureHours}}</td>
        <td>{{dot.minimalPoint}}</td>
        <td>{{dot.averagePoint}}</td>
        <td>{{dot.person.name}}</td>
        <td>{{dot.person.eyeColor}}</td>
        <td>{{dot.person.hairColor}}</td>
        <td>{{dot.person.location.x}}</td>
        <td>{{dot.person.location.y}}</td>
        <td>{{dot.person.location.z}}</td>
        <td>{{dot.person.weight}}</td>
        <td>{{dot.person.nationality}}</td>
        <td>{{dot.permission}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
