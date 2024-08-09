<script setup lang="ts">
const emit = defineEmits(['edit', 'delete', 'show'])
const {thead, tbody, title} = defineProps(['thead', 'tbody', 'title'])
</script>

<template>
  <section class="bg-white flex flex-col gap-y-3  p-5 rounded">
    <div><h3 class="font-bold">{{ title }}</h3></div>
    <table>
      <thead>
      <tr>
        <th v-for="(item , index) in thead" :key="index">{{ item.name }}</th>
      </tr>
      </thead>
      <tbody v-if="tbody && tbody.length ">
      <tr v-for="(item , index) in tbody" :key="index" :class="{'bg-red-100':item.is_deleted === true}">
        <td>{{ index + 1 }}</td>
        <td>{{item.studentCode}}</td>
        <td>{{ item.first_name }}</td>
        <td>{{ item.last_name.length > 30 ? item.last_name.substr(0, 15) + '...' : item.last_name }}</td>
        <td>{{ item.nationalCode }}</td>
        <td><a :href="`tel:${item.phoneNumber}`">{{ item.phoneNumber }}</a></td>
        <td><a :href="`tel:${item.emergencyPhoneNumber}`">{{ item.emergencyPhoneNumber }}</a></td>
        <td>{{ item.studentAge }}</td>
        <td class="  rounded status"><span
            :class="` w-fit mx-auto px-3 py-1 rounded ${ item.is_active ? 'bg-blue-500' :'bg-red-500'}`">{{
            item.is_active === true ? 'فعال' : "غیرفعال"
          }}</span>
        </td>
        <td class=" py-1 ">
          <button
              @click="emit('show',tbody[index])"
              class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-blue-700 text-white bg-blue-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-eye fa-xs"/>
          </button>
          <button
              @click="emit('edit',item.id)"
              class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-yellow-700 text-white bg-yellow-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-pen fa-xs"/>
          </button>
          <button
              @click="emit('delete', {name:item.first_name , lastName:item.last_name , id:item.id})"
              :disabled="item.is_deleted"
              :class="{'opacity-30':item.is_deleted , 'cursor-not-allowed' : item.is_deleted}"
              class=" mx-1 cursor-pointer transition  duration-200 ease-in hover:bg-red-700 text-white bg-red-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-trash-alt fa-xs"/>
          </button>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped lang="scss">
table, th, td {
  border: 1px solid rgba(0, 0, 0, 0.15);
  text-align: center;
  font-size: var(--text-lg);
  font-weight: var(--font-medium);
  padding-top: 5px;
  padding-bottom: 5px;

}

th,td{
  padding: 5px 7px;
}

th {
  font-size: var(--text-md);
  font-weight: var(--font-bold);
}

.status {
  color: #fff;

  span {
    font-size: var(--text-md);
    display: flex;
    justify-content: center;
  }
}
</style>
