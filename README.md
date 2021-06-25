# Actuator
- http://localhost:8080/actuator

# Prometheus
`docker-compose up`
- http://localhost:9090

- [config template](https://github.com/prometheus/prometheus/blob/release-2.28/config/testdata/conf.good.yml)
- [alert config](https://tomgregory.com/monitoring-a-spring-boot-application-part-3-rules-and-alerting/)
- [templates](https://awesome-prometheus-alerts.grep.to/rules.html#jvm)

# Application
- http://localhost:8080/version
- http://localhost:8080/cpuUsage/factorial/5
- http://localhost:8080/memoryUsage