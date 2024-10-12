export default defineNuxtPlugin(() => {
    return {
        provide: {
            date: (value) => {
                if (value) {
                    const d = new Date(value);
                    return new Intl.DateTimeFormat("fa-IR").format(d);
                }
            },
        },
    };
});
