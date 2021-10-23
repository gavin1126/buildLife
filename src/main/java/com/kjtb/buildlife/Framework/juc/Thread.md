


到底什么是线程？就是一个程序执行上下文，context of execution 
https://stackoverflow.com/questions/5201852/what-is-a-thread-really

context：上下文，环境信息，注册表，一系列的相关注册表/账簿
execution context (therefore a thread) consists of the values of the CPU's registers.
它会记录关于某事物当前状态的所有信息，便于恢复，或其它用途

---
A thread is a context of execution, 
while a process is a bunch of resources associated with a computation. 
A process can have one or many threads.
---
the resources associated with a process include memory pages (all the threads in a process have the same view of the memory), file descriptors (e.g., open sockets), and security credentials (e.g., the ID of the user who started the process).


