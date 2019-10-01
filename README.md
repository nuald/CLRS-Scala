# CLRS-Scala

CLRS related Jupyter notebooks using Scala kernel.

## Installation

Jupyter Notebook (requires Python installed):

    $ pip3 install --upgrade pip
    $ pip3 install jupyter

Almond (Scala) kernel:

    $ SCALA_VERSION=2.13.0 ALMOND_VERSION=0.8.2
    $ curl -Lo coursier https://git.io/coursier-cli
    $ chmod +x coursier
    $ ./coursier bootstrap \
        -r jitpack \
        -i user -I user:sh.almond:scala-kernel-api_$SCALA_VERSION:$ALMOND_VERSION \
        sh.almond:scala-kernel_$SCALA_VERSION:$ALMOND_VERSION \
        -o almond
    $ ./almond --install

## Usage

Run the required notebook from the web-interface:

    $ jupyter notebook
