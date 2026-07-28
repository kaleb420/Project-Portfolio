# BRiscOS
BRiscOS is a microkernel designed for educational and research purposes.  In its initial state, it provides only ~200 lines of code which support basic bootloading and interrupt delegation; landing in an infinite loop in `user space`.  The OS unfolds through iterative milestones checked out using the build tools.

## Documentation

You can find a full set of documentation - including function API and milestone project descriptions - at the [associated repository wiki](../../wiki).

## Building BRiscOS
BRiscOS uses `SConstruct`; a _python3_ based build system.  We provide a handful of commands to checkout milestone templates, build the OS, run the OS on QEMU as a flat binary or invoked through uboot as an EFI, run milestone-based unit tests, and launch a GDB server for debugging purposes.

### Your First Build

> [!NOTE]
> The below instructions use the `$USER` bash variable under the assumption that you are using a fork of the repository under your own account and that your local username matches your github username.
> If either of these is not true, you should replace the `$USER` variable with any org or user name as needed.

In order to begin working with BRiscOS, first clone the repository into a local copy.

```
git clone git@github.iu.edu:$USER/educational -o briscos
```

> [!WARNING]
> You may need to install `scons` with the below to use the build tools.
> ```
> pip install scons
> ```

In this intial state, the OS will not initially compile - due to a missing device driver - but it the repository will now contain the ~200 line core framework that BRiscOS builds around.  Checking out further milestones will extend the code base to include additional functionality.

### Checking Out a Milestone

Once the repository has been cloned, we will use `scons` inside the repo directory.

```
scons checkout milestone=1
```

This will generate the necessary <kbd>uart</kbd> device driver code in order to print kernel panics to the screen (the missing link preventing us from compiling immediately), as well as the template code for the first milestone project.  If you are building the kernel as a student, this is where the work begins.  While the code will compile and run now, it will not display any text until you complete the `printf` implementation.

### Compiling and Running BRiscOS

You may compile the code at any time using the following command:

```
scons build
```

And run the code using:

```
scons run
```

> [!TIP]
> the `build` target is considered a dependency of the `run` target, so if you call `run` and there are files that are not up-to-date, the build tools will automatically run `build` for you.

#### [Optional] Running With No Bootloader

The above `run` command will run the system in QEMU with a bootloader called <kbd>u-boot</kbd>.  This takes some time to boot, and the BRiscOS image is designed to also be directly loadable.  You can do that with the `--mode=metal` option.

```
scons run --mode=metal
```

## Testing and Debugging

The build tools also provide aliases to common testing and debugging tools.  Each milestone includes a suite of unit tests, selected whenever the `milestone` flag is passed to a command (such as the above `checkout` target).  These tests can be invoked by using the `test` target:

```
scons test
```

### GDB Aliases

You can run the OS in a QEMU instance with a GDB server by using the `--mode=debug` option:

```
scons run --mode=debug
```
> [!TIP]
> If you are working in a shared environment, you can manually set the port for the GDB server with the `--port=#` option
> ```
> scons run --mode=debug --port=5555
> ```

In order to connect to the GDB server, you can either start GDB and set up the connection manually, or use the corresponding target (with port flag if necesssary).
```
scons gdb
```
