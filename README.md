# ContentProviderRoom

Изначально открывается ACtivityMain, выводится на экран activity_main к которой привязан StartFragment. Создаётся StartFragmentViewModel,
в которой инициализируется room repository и contentProvider, что даёт доступ к ним. Далее мы берём из них данные и добавляем нужные контакты в бд, через viewmodelscope
У меня ошибка где-то в работе viewModelScope. Приложение крашится без ошибок.
